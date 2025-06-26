import os 
from musica import app
from flask_wtf import FlaskForm
from wtforms import StringField, validators, SubmitField, PasswordField

class FormularioMusica(FlaskForm):

    nome = StringField('Nome da música', [validators.DataRequired(), validators.length(min=2, max=50)])
    grupo = StringField('Cantor', [validators.DataRequired(), validators.length(min=2, max=30)])
    genero = StringField('Gênero', [validators.DataRequired(), validators.length(min=2, max=30)])

    cadastrar = SubmitField('Cadastrar música')


class FormularioUsuario(FlaskForm):

    usuario = StringField('Usuário', [validators.DataRequired(), validators.length(min=2, max=20)])
    senha = PasswordField('Senha', [validators.DataRequired(), validators.length(min=2, max= 10)])

    logar = SubmitField('Entrar')


class FormularioCadastro(FlaskForm):

    nome = StringField('Nome do usuário', [validators.DataRequired(), validators.length(min=2, max=55)])
    usuario = StringField('Usuário', [validators.DataRequired(), validators.length(min=2, max=20)])
    senha = PasswordField('Senha', [validators.DataRequired(), validators.length(min=2, max=255)])

    cadastrar = SubmitField('Cadastrar')

def recupera_imagem(id):
    for nm_image in os.listdir(app.config['UPLOAD_FOLDER']):

        nome = str(nm_image)
        
        nome = nome.split('.')

        if f'album{id}_' in nome[0]:
            return nm_image
        
    return 'default.png'

def deletar_imagem(id):

    imagem_excluir = recupera_imagem(id)
    if imagem_excluir != 'default.png':
        os.remove(os.path.join(app.config['UPLOAD_FOLDER'], imagem_excluir))