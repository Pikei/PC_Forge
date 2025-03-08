from product.Product import Product


class GraphicsCard(Product):
    def __init__(self, name, producer, category, description, price, producer_code, chipset_producer, chipset_type,
                 memory, bus, card_length, recommended_power, cooling_type):
        super().__init__(name, producer, category, description, price, producer_code)
        self.chipset_producer = chipset_producer
        self.chipset_type = chipset_type
        self.memory = memory
        self.bus = bus
        self.card_length = card_length
        self.recommended_power = recommended_power
        self.cooling_type = cooling_type