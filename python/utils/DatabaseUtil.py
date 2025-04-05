import os

from sqlalchemy.orm import sessionmaker
import sqlalchemy as sa

from product.Case import Case, CaseMotherboardCompatibility
from product.Cooler import Cooler, CoolerCpuCompatibility
from product.Drive import Drive, DriveInterfaces
from product.Motherboard import Motherboard, MB_Standard
from product.Processor import Processor, CpuSocket
from product.Product import Product

database_url = os.environ.get("DATABASE_URL")
db = sa.create_engine(database_url)
Session = sessionmaker(bind=db)
session = Session()


class DatabaseUtil:
    def __init__(self, products: dict[int:Product]):
        self.products = products

    def add_products_to_database(self):
        counter = 0
        for prod in self.products.values():
            counter = counter + 1
            product_db = session.query(Product).filter_by(ean=prod.get_ean()).first()
            if product_db is None:
                print(f"[{int((counter * 100) / len(self.products))}%] Adding", prod.get_name(), "(", prod.get_ean(),
                      ") to Database")
                if isinstance(prod, Processor):
                    prod.set_socket_id(self.get_or_create_socket(prod.get_socket()))
                    session.add(prod)
                elif isinstance(prod, Motherboard):
                    prod.set_socket_id(self.get_or_create_socket(prod.get_cpu_socket()))
                    prod.set_standard_id(self.get_or_create_mb_standard(prod.get_standard()))
                    session.add(prod)
                elif isinstance(prod, Case):
                    session.add(prod)
                    self.mb_case_compatibility(prod.get_ean(), prod.get_mb_compatibility())
                elif isinstance(prod, Cooler):
                    session.add(prod)
                    self.cooler_socket_compatibility(prod.get_ean(), prod.get_socket_compatibility())
                elif isinstance(prod, Drive):
                    prod.set_drive_interface_id(self.get_or_create_drive_interface(prod.get_interface()))
                    session.add(prod)
                else:
                    session.add(prod)
            else:
                print("Product", prod.get_name(), "(", prod.get_ean(), ") already exists")

        print("Committing data to database")
        session.commit()

    @staticmethod
    def get_or_create_socket(socket):
        db_socket = session.query(CpuSocket).filter_by(name=socket).first()
        if db_socket:
            return db_socket.id

        new_socket = CpuSocket(name=socket)
        session.add(new_socket)
        session.flush()
        return new_socket.id

    @staticmethod
    def get_or_create_mb_standard(standard):
        db_standard = session.query(MB_Standard).filter_by(name=standard).first()
        if db_standard:
            return db_standard.id

        db_standard = MB_Standard(name=standard)
        session.add(db_standard)
        session.flush()
        return db_standard.id

    @staticmethod
    def mb_case_compatibility(ean, compatibility_list):
        db_standards = session.query(MB_Standard).all()
        for standard in db_standards:
            if standard.name in compatibility_list:
                compatibility = CaseMotherboardCompatibility(standard_id=standard.id, ean=ean)
                session.add(compatibility)

    @staticmethod
    def cooler_socket_compatibility(ean, compatibility_list):
        db_sockets = session.query(CpuSocket).all()
        for comp in compatibility_list:
            if comp not in db_sockets:
                socket = CpuSocket(name=comp)
                session.flush()
                session.add(socket)

        for socket in db_sockets:
            if socket.name in compatibility_list:
                compatibility = CoolerCpuCompatibility(socket_id=socket.id, ean=ean)
                session.add(compatibility)

    @staticmethod
    def get_or_create_drive_interface(drive_interface):
        db_interface = session.query(DriveInterfaces).filter_by(name=drive_interface).first()
        if db_interface:
            return db_interface.id

        db_interface = DriveInterfaces(name=drive_interface)
        session.add(db_interface)
        session.flush()
        return db_interface.id
