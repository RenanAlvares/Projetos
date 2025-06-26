from musica import db

class Musica(db.Model):
    id = db.Column(db.Integer, primary_key = True, autoincrement = True)
    nm_music = db.Column(db.String(50), nullable = False)
    cantor = db.Column(db.String(30), nullable = False)
    gen = db.Column(db.String(30), nullable = False)

    def __repr__(self):
        return f'<Musica {self.nm_music}>'


class Usuario(db.Model):
    id_user = db.Column(db.Integer, primary_key = True, autoincrement = True)
    nm_user = db.Column(db.String(55), nullable = False)
    login = db.Column(db.String(20), nullable = False)
    senha = db.Column(db.String(10), nullable = False)

    def __repr__(self):
        return f'<Usuario {self.nm_user}>'