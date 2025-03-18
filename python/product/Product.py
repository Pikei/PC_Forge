class Product:
    def __init__(self, name: str, producer: str, category: str, description: str, price: float, producer_code: str):
        self.name: str = name
        self.producer = producer
        self.category = category
        self.description = description
        self.price = price
        self.producer_code = producer_code

    def print_common(self):
        print("--------------- ", self.name, " ---------------")
        print("Product type:", self.category)
        print("Producer:", self.producer)
        print("Producer code:", self.producer_code)
        print("Price [PLN]:", self.price)
        print("-- DESCRIPTION --")
        print(self.description.replace("<", "\n<").replace("\n</", "</"))

    def print_end(self):
        print("---------------------------------------\n")

    def print_product_specs(self):
        pass
