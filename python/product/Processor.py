from product.Product import Product


class Processor(Product):
    def __init__(self, name: str, producer: str, category: str, description_headers: list[str],
                 description_paragraphs: list[str], price: float, producer_code: str,
                 line: str, model: str, cores: int, threads: int, socket: str, unlocked: bool, frequency: float,
                 max_frequency: float, integrated_graphics_unit: bool, tdp: int, cooler_included: bool, packaging: str):
        super().__init__(name, producer, category, description_headers, description_paragraphs, price, producer_code)
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
        self.packaging = packaging

    def print_product_specs(self):
        super().print_common()
        print("--- SPECS ---")
        print("Line:", self.line)
        print("Model:", self.model)
        print("Number of cores:", self.cores)
        print("Number of threads:", self.threads)
        print("CPU socket:", self.socket)
        print("Unlocked:", self.unlocked)
        print("Frequency [GHz]:", self.frequency)
        print("Max frequency [GHz]:", self.max_frequency)
        print("Integrated Graphics Unit:", self.integrated_graphics_unit)
        print("TDP [W]:", self.tdp)
        print("Cooler included:", self.cooler_included)
        print("Packaging:", self.packaging)
        super().print_end()
