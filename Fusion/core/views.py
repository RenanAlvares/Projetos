from django.views.generic import FormView
from .forms import ContatoForm
from django.urls import reverse_lazy
from django.contrib import messages

from .models import Servico, Funcionario, Feature

class IndexView(FormView):
    template_name = 'index.html'
    form_class = ContatoForm
    success_url = reverse_lazy('index')

    def get_context_data(self, **kwargs):

        context = super(IndexView, self).get_context_data(**kwargs)
        context['servicos'] = Servico.objects.order_by('?').all()
        context['funcionarios'] = Funcionario.objects.order_by('?').all()
        context['features'] = Feature.objects.order_by('?').all()
        return context
    
    def form_valid(self, form, *args, **kwags):
        form.send_mail()
        messages.success(self.request, 'E-mail enviado com sucesso!')
        return super(IndexView, self).form_valid(form, *args, **kwags)
    
    def form_invalid(self, form, *args, **kwags):
        messages.error(self.request, 'Erro ao enviar e-mail.')
        return super(IndexView, self).form_invalid(form, *args, **kwags)









