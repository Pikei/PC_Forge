import os


class CommonUtils:
    @staticmethod
    def directory_exists(directory_name: str):
        try:
            os.mkdir(directory_name)
            print(f"Directory '{directory_name}' created successfully.")
            return True
        except FileExistsError:
            print(f"Directory '{directory_name}' already exists.")
            return True
        except Exception as e:
            print(f"An error occurred: {e}")
            return False
