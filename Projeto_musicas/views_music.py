from musica import db, app
from models import Musica, Usuario
from flask import render_template, request, redirect, session, flash, url_for, send_from_directory
from definitions import recupera_imagem, deletar_imagem, FormularioMusica, FormularioUsuario
import time

@app.route('/')


def listarMusicas():
 
    if 'usuario_logado' not in session or session['usuario_logado'] is None:
        return redirect(url_for('login'))
    
    lista = Musica.query.order_by(Musica.id)

    return render_template('lista_musicas.html', titulo='Músicas cadastradas', musicas = lista)

@app.route('/cadastrar')

def cadastrar():

    if 'usuario_logado' not in session or session['usuario_logado'] is None:
        
        return redirect(url_for('login'))
    
    form = FormularioMusica()

    return render_template('cadastrar_musica.html', titulo='Cadastrar música',form=form)


@app.route('/adicionar', methods=['POST',])

def adicionar():

    formRecebido = FormularioMusica(request.form)

    if not formRecebido.validate_on_submit():
        return redirect(url_for('cadastrar'))

    name = formRecebido.nome.data
    singer = formRecebido.grupo.data
    gen = formRecebido.genero.data

    musica = Musica.query.filter_by(nm_music=name).first()

    if musica:
        flash('Música já está cadastrada')
        return redirect(url_for('listarMusicas'))
    
    try:
        nova_musica = Musica(nm_music=name, cantor=singer, gen=gen)
        db.session.add(nova_musica)
        db.session.commit()

        flash('Música cadastrada com sucesso!')
    except Exception as e:
        db.session.rollback()
        flash(f'Erro ao cadastrar música: {str(e)}')

    arquivo = request.files['arquivo']

    if arquivo:

        folder = app.config['UPLOAD_FOLDER']

        nome_arquivo = arquivo.filename
        nome_arquivo = nome_arquivo.split('.')
        extensao = nome_arquivo[len(nome_arquivo)-1]

        momento = time.time()

        nm_completo = f'album{nova_musica.id}_{momento}.{extensao}'

        arquivo.save(f'{folder}/{nm_completo}')

    return redirect(url_for('listarMusicas'))



@app.route('/editar/<int:id>')

def editar(id):
    if 'usuario_logado' not in session or session['usuario_logado'] is None:
        return redirect(url_for('login'))
    
    msc_search = Musica.query.filter_by(id=id).first()

    form = FormularioMusica()

    form.nome.data = msc_search.nm_music
    form.grupo.data = msc_search.cantor
    form.genero.data = msc_search.gen

    album = recupera_imagem(id)

    return render_template('editar_musica.html', titulo='Editar Música', musica=form, album_musica=album, id=id)


@app.route('/atualizar', methods=['POST',])

def atualizar():
    formRec = FormularioMusica(request.form)

    if formRec.validate_on_submit():

        song = Musica.query.filter_by(id=request.form['txtID']).first()

        song.nm_music = formRec.nome.data
        song.cantor = formRec.grupo.data
        song.gen = formRec.genero.data

        db.session.add(song)

        db.session.commit()

        arquivo = request.files['arquivo']

        if arquivo:
            pasta_upload = app.config['UPLOAD_FOLDER']

            nome_arquivo = arquivo.filename
            nome_arquivo = nome_arquivo.split('.')

            extensao = nome_arquivo[len(nome_arquivo)-1]

            momento = time.time()
            nome_completo = f'album{song.id}_{momento}.{extensao}'

            deletar_imagem(song.id)

            arquivo.save(f'{pasta_upload}/{nome_completo}')
        
        flash('Música editada com sucesso!')

    return redirect(url_for('listarMusicas'))


@app.route('/excluir/<int:id>')

def excluir(id):
    if 'usuario_logado' not in session or session['usuario_logado'] is None:
        return redirect(url_for('login'))

    Musica.query.filter_by(id=id).delete()
    deletar_imagem(id)
    db.session.commit()
    flash('Música excluída com sucesso!')

    return redirect(url_for('listarMusicas'))

@app.route('/uploads/<nome_img>')

def imagem(nome_img):
    return send_from_directory('uploads', nome_img)


