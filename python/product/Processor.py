from Product import Product

class Processor(Product):
    def __init__(self, name, producer, category, description, price, line, model, cores, socket, frequency, max_frequency, tdp):
        super().__init__(name, producer, category, description, price)
        self.line = line
        self.model = model
        self.cores = cores
        self.socket = socket
        self.frequency = frequency
        self.max_frequency = max_frequency
        self.tdp = tdp
