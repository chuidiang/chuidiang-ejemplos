class persona:
    def __init__ (self, nombre, email):
        self.nombre=nombre
        self.email=email


class empleado(persona):
    def __init__(self, nombre, email, email_empresa):
        persona.__init__(self, nombre, email)
        self.email_empresa=email_empresa
