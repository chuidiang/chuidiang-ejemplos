class corredor:
    def __init__(self,marca_corredor):
        self.marca_corredor=marca_corredor

class nadador:
    def __init__(self,marca_nadador):
        self.marca_nadador=marca_nadador

class ciclista:
    def __init__(self,marca_ciclista):
        self.marca_ciclista=marca_ciclista

class triatleta(corredor,nadador,ciclista):
    def __init__(self,marca_corredor,marca_nadador,marca_ciclista):
        corredor.__init__(self,marca_corredor)
        nadador.__init__(self,marca_nadador)
        ciclista.__init__(self,marca_ciclista)

