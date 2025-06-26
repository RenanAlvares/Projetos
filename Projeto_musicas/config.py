import os

SECRET_KEY = 'teste123'

SQLALCHEMY_DATABASE_URI = '{SGBD}://{usuario}:{senha}@{servidor}/{database}'.format(
    SGBD = 'mysql+pymysql',
    usuario = 'root',
    senha = 'Renan123',
    servidor = 'localhost',
    database = 'playmusica'
)

UPLOAD_FOLDER = os.path.dirname(os.path.abspath(__file__)) + '/uploads'

