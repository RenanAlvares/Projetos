from django.shortcuts import render
from django.shortcuts import get_object_or_404

from core.models import Produto
from django.http import HttpResponse
from django.template import loader

produtos = Produto.objects.all()


def index(request):

    context = {
        'teste': 'linha 1',
        'teste 2': 'linha 2',
        'produtos': produtos
    }
    return render(request, 'index.html', {'produtos' : produtos})

def contato(request):
    return render(request, 'contato.html')

def produto(request, pk):
    #prod = Produto.objects.get(id=pk)
    prod = get_object_or_404(Produto, id=pk)
    
    context = {
        'produto': prod,
    }
    return render(request, 'produto.html',context)


def error404(request, ex):
    template = loader.get_template('404.html')
    return HttpResponse(content=template.render(), content_type='text/html; charset=utf8', status=404)

def error500(request):
    template = loader.get_template('500.html')
    return HttpResponse(content=template.render(), content_type='text/html; charset=utf8', status=500)


