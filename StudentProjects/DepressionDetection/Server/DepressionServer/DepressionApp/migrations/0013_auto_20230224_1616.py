# Generated by Django 3.2.18 on 2023-02-24 10:46

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('DepressionApp', '0012_auto_20230224_1604'),
    ]

    operations = [
        migrations.AlterField(
            model_name='questions',
            name='correct_option',
            field=models.CharField(default='None', max_length=255),
        ),
        migrations.AlterField(
            model_name='questions',
            name='option3',
            field=models.CharField(default='empty', max_length=255),
        ),
        migrations.AlterField(
            model_name='questions',
            name='option4',
            field=models.CharField(default='None', max_length=255),
        ),
    ]
