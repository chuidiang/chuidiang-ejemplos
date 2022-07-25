class poligono:
    def dame_area(self):
        pass

class cuadrado(poligono):
    def __init__ (self, lado):
        self.lado = lado

    def dame_area(self):
        return self.lado*self.lado
