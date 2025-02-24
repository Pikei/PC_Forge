from product.Product import Product


class Case(Product):
    def __init__(self, name, producer, category, description, price, case_type, max_gpu_length, max_cooler_height,
                 color, rgb, window, height, width, depth, compatibilities, fans, fans_diameter, usb_ports):
        super().__init__(name, producer, category, description, price)
        self.case_type = case_type
        self.max_gpu_length = max_gpu_length
        self.max_cooler_height = max_cooler_height
        self.color = color
        self.rgb = rgb
        self.window = window
        self.height = height
        self.width = width
        self.depth = depth
        self.compatibilities = compatibilities
        self.fans = fans
        self.fans_diameter = fans_diameter
        self.usb_ports = usb_ports
