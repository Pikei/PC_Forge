from product.Product import Product


class Cooler(Product):
    def __init__(self, name, producer, category, description, price, producer_code, fan_speed, fan_diameter, fans,
                 height, width, depth, rgb, cpu_sockets):
        super().__init__(name, producer, category, description, price, producer_code)
        self.fan_speed = fan_speed
        self.fan_diameter = fan_diameter
        self.fans = fans
        self.height = height
        self.width = width
        self.depth = depth
        self.rgb = rgb
        self.cpu_sockets = cpu_sockets