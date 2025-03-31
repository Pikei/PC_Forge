class Product:
    def __init__(self, name: str, producer: str, category: str, description: str, price: float, producer_code: str,
                 ean: int):
        self.name: str = name
        self.producer: str = producer
        self.category: str = category
        self.description: str = description
        self.price: float = price
        self.producer_code: str = producer_code
        self.ean: int = ean

    def print_end(self):
        print("-- DESCRIPTION --")
        print(self.description.replace("<", "\n<").replace("\n</", "</"))
        print("---------------------------------------\n")

    def print_product_specs(self):
        print("--------------- ", self.name, " ---------------")
        print("Product type:", self.category)
        print("Producer:", self.producer)
        print("Producer code:", self.producer_code)
        print("EAN:", self.ean)
        print("Price [PLN]:", self.price)
        print("--- SPECS ---")


    def get_name(self):
        return self.name

    def set_name(self, name: str):
        self.name = name

    def get_producer(self):
        return self.producer

    def set_producer(self, producer: str):
        self.producer = producer

    def get_category(self):
        return self.category

    def set_category(self, category: str):
        self.category = category

    def get_description(self):
        return self.description

    def set_description(self, description: str):
        self.description = description

    def get_price(self):
        return self.price

    def set_price(self, price: float):
        self.price = price

    def get_producer_code(self):
        return self.producer_code

    def set_producer_code(self, producer_code: str):
        self.producer_code = producer_code

    def get_ean(self):
        return self.ean

    def set_ean(self, ean: int):
        self.ean = ean
