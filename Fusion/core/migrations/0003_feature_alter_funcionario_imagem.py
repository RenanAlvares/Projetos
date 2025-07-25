# Generated by Django 5.1.5 on 2025-07-09 22:45

import core.models
import stdimage.models
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('core', '0002_alter_funcionario_cargo_alter_funcionario_imagem'),
    ]

    operations = [
        migrations.CreateModel(
            name='Feature',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('criados', models.DateField(auto_now_add=True, verbose_name='Criação')),
                ('modificado', models.DateField(auto_now_add=True, verbose_name='Atualização')),
                ('ativo', models.BooleanField(default=True, verbose_name='Ativo')),
                ('nome', models.CharField(max_length=100, verbose_name='Nome')),
                ('descricao', models.TextField(max_length=200, verbose_name='Descrição')),
                ('icone', models.CharField(choices=[('lni-rocket', 'Foguete'), ('lni-laptop', 'Laptop'), ('lni-engine', 'Engrenagem'), ('lni-leaf', 'Folha'), ('lni-squads', 'Quadros')], max_length=12, verbose_name='Icone')),
            ],
            options={
                'verbose_name': 'Feature',
                'verbose_name_plural': 'Features',
            },
        ),
        migrations.AlterField(
            model_name='funcionario',
            name='imagem',
            field=stdimage.models.StdImageField(force_min_size=False, upload_to=core.models.get_file_path, variations={'thumb': {'crop': True, 'height': 480, 'width': 480}}, verbose_name='Imagem'),
        ),
    ]
