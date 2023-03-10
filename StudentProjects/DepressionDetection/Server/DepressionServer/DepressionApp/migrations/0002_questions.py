# Generated by Django 3.2.18 on 2023-02-20 10:37

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('DepressionApp', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='questions',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('question', models.CharField(max_length=255)),
                ('option1', models.CharField(max_length=30)),
                ('option2', models.CharField(max_length=30)),
                ('option3', models.CharField(max_length=30)),
                ('option4', models.CharField(max_length=30)),
                ('correct_option', models.CharField(max_length=30)),
            ],
            options={
                'db_table': 'questions',
                'managed': True,
            },
        ),
    ]
