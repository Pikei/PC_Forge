from product.Product import Product


class Processor(Product):
    def __init__(self, name, producer, category, description, price, producer_code, line, model, cores, threads, socket,
                 unlocked, frequency, max_frequency, integrated_graphics_unit, tdp, cooler_included):
        super().__init__(name, producer, category, description, price, producer_code)
        self.line = line
        self.model = model
        self.cores = cores
        self.threads = threads
        self.socket = socket
        self.unlocked = unlocked
        self.frequency = frequency
        self.max_frequency = max_frequency
        self.integrated_graphics_unit = integrated_graphics_unit
        self.tdp = tdp
        self.cooler_included = cooler_included
