# Generated by Django 3.2.16 on 2022-12-23 01:38

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='accounts',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=30)),
                ('username', models.CharField(max_length=30)),
                ('password', models.CharField(max_length=50)),
                ('email', models.CharField(max_length=30)),
                ('phone', models.CharField(max_length=15)),
            ],
        ),
    ]
