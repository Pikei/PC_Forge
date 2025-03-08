from product.Product import Product


class PowerSupply(Product):
    def __init__(self, name, producer, category, description, price, producer_code, certification, efficiency, power,
                 modularity, standard, fan_diameter, height, width, depth):
        super().__init__(name, producer, category, description, price, producer_code)
        self. certification = certification
        self. efficiency = efficiency
        self. power = power
        self. modularity = modularity
        self. standard = standard
        self. fan_diameter = fan_diameter
        self. height = height
        self. width = width
        self. depth = depth