class Product:
    def __init__(self, name: str, producer: str, category: str, description_headers: list[str],
                 description_paragraphs: list[str], price: float, producer_code: str):
        self.name: str = name
        self.producer = producer
        self.category = category
        self.description_headers = description_headers
        self.description_paragraphs = description_paragraphs
        self.price = price
        self.producer_code = producer_code

    def print_common(self):
        print("--------------- ", self.name, " ---------------")
        print("Product type:", self.category)
        print("Producer:", self.producer)
        print("Producer code:", self.producer_code)
        print("Price [PLN]:", self.price)
        print("-- DESCRIPTION --")
        for i in range(0, len(self.description_headers)):
            print(self.description_headers[i])
            print(self.description_paragraphs[i])

    def print_end(self):
        print("---------------------------------------\n")

    def print_product_specs(self):
        pass
