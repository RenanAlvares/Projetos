import MySQLdb


def conectar():
    """
    Função para conectar ao servidor
    """
    try:
        conn = MySQLdb.connect(
            db='pmysql',
            host='localhost',
            user='root',
            passwd='Renan123'
        )
        return conn
    except MySQLdb.Error as e:
        print(f'Erro na conexão do MySQL server {e}')

def desconectar(conn):
    """ 
    Função para desconectar do servidor.
    """
    if conn:
        conn.close()


def listar():
    """
    Função para listar os produtos
    """
    conn = conectar()
    cursor = conn.cursor()
    cursor.execute('select * from produtos')

    produtos = cursor.fetchall()

    if len(produtos) > 0:
        print('listando produtos: ')
        print('--------------------------------------')
        for produto in produtos:
            print(f'ID: {produto[0]}')
            print(f'PRODUTO: {produto[1]}')
            print(f'PRECO: {produto[2]}')
            print(f'ESTOQUE: {produto[3]}')
        print('--------------------------------------')
    else:
        print('Não existem produtos cadastrados')
    
    desconectar(conn)

def inserir():
    """
    Função para inserir um produto
    """  
    conn = conectar()
    cursor = conn.cursor()

    nome = input('Informe o nome do produto: ')
    preco = float(input('Informe o preço do produto: '))
    estoque = int(input('Informe a quantidade em estoque: '))

    cursor.execute(f"insert into produtos (nome, preco, estoque) values ('{nome}', {preco}, {estoque})")
    conn.commit()

    if cursor.rowcount == 1:
        print(f'O produto {nome} foi inserido corretamente')
    else:
        print('Não foi possível inserir o produto')

    desconectar(conn)


def atualizar():
    """
    Função para atualizar um produto
    """
    conn = conectar()
    cursor = conn.cursor()

    codigo = input('Informe o código do produto: ')
    nome = input('Informe o novo nome do produto: ')
    preco = float(input('Informe o novo preco do produto: '))
    estoque = int(input('Informe a nova quantidade em estoque: '))

    cursor.execute(f"update produtos set nome='{nome}', preco={preco}, estoque={estoque} where id={codigo}")
    conn.commit()

    if cursor.rowcount == 1:
        print(f'O produto {nome} foi atualizado com sucesso')
    else:
        print('Não foi possível atualizar o produto.')

    desconectar(conn)

def deletar():
    """
    Função para deletar um produto
    """  
    conn = conectar()
    cursor = conn.cursor()

    codigo = int(input('Informe o código do produto: '))

    cursor.execute(f'DELETE FROM produtos WHERE id={codigo}')
    conn.commit()

    if cursor.rowcount == 1:
        print('Exclusão realizada com sucesso.')
    else:
        print('Não foi possível realizar a exclusão do produto')

    desconectar(conn)

def menu():
    """
    Função para gerar o menu inicial
    """
    while True:
        print('=========Gerenciamento de Produtos==============')
        print('Selecione uma opção: ')
        print('1 - Listar produtos.')
        print('2 - Inserir produtos.')
        print('3 - Atualizar produto.')
        print('4 - Deletar produto.')
        print('5 - Sair do programa.')

        opcao = int(input())
        if opcao in [1, 2, 3, 4, 5]:
            if opcao == 1:
                listar()
            elif opcao == 2:
                inserir()
            elif opcao == 3:
                atualizar()
            elif opcao == 4:
                deletar()
            elif opcao == 5:
                print('Saindo...')
                break
            else:
                print('Opção inválida')
        else:
            print('Opção inválida, digite um número válido!')
