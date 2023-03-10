# Generated by Django 3.2.16 on 2023-02-27 12:18

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('PersonalityApp', '0002_apptitude_question'),
    ]

    operations = [
        migrations.CreateModel(
            name='personality_question',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('question', models.CharField(max_length=250)),
                ('openness_to_experience', models.CharField(max_length=250)),
                ('conscientiousness', models.CharField(max_length=250)),
                ('extraversion', models.CharField(max_length=250)),
                ('agreeableness', models.CharField(max_length=250)),
                ('neuroticism', models.CharField(max_length=250)),
            ],
        ),
    ]
