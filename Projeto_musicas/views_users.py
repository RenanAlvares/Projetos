from flask import render_template, request, redirect, session, flash, url_for, send_from_directory
from models import Musica, Usuario
from musica import db, app
from definitions import FormularioUsuario, FormularioCadastro
from flask_bcrypt import generate_password_hash, check_password_hash

@app.route('/login')

def login():

    form = FormularioUsuario()

    return render_template('login.html', form=form)


@app.route('/autenticar', methods=['POST',] )

def autenticar():

    from models import Usuario

    form = FormularioUsuario(request.form)

    usuario = Usuario.query.filter_by(login = form.usuario.data).first()

    senha = check_password_hash(usuario.senha, form.senha.data)

    if usuario and senha:

        session['usuario_logado'] = usuario.login
        flash(f'Seja bem vindo, {usuario.login}!')
        return redirect(url_for('listarMusicas'))
    
    else:

        flash("Usuário ou senha inválidos")
        return redirect(url_for('login'))
    

@app.route('/cadastro')

def cadastro():

    if 'usuario_logado' not in session or session['usuario_logado'] is None:
        return redirect(url_for('login'))
    
    form = FormularioCadastro()

    return render_template('cadastro_user.html', titulo='Cadastrar usuário', form=form)


@app.route('/addUsuario', methods=['POST',])

def addUser():
    form = FormularioCadastro(request.form)

    if not form.validate_on_submit():
        return redirect(url_for('cadastro'))
    
    nome = form.nome.data
    usuario = form.usuario.data
    senha = generate_password_hash(form.senha.data).decode('utf-8')

    user_existe = Usuario.query.filter_by(login=usuario).first()

    if user_existe:
        flash('Usuário já cadastrado!')
        return redirect(url_for('cadastro'))
    
    novo_usuario = Usuario(nm_user=nome, login=usuario, senha=senha)

    db.session.add(novo_usuario)

    db.session.commit()

    flash('Usuário cadastrado com sucesso!')

    return redirect(url_for('listarMusicas'))


@app.route('/sair')

def sair():
    session['usuario_logado'] = None
    return redirect('/login')