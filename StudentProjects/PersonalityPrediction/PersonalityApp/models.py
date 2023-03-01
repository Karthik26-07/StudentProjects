from django.db import models

# Create your models here.

class User(models.Model):

    class Meta:
        db_table = 'user'

    name = models.CharField(blank=False, max_length=50)
    contact = models.CharField(blank=False, max_length=50)
    email = models.CharField(blank=False, max_length=50)
    address = models.CharField(blank=False, max_length=50)
    user_name = models.CharField(blank=False, max_length=25, default=None)
    password = models.CharField(max_length=10, blank=False, default=None)



class Questions(models.Model):
    class Meta:
        db_table="questions"

    type_id=models.IntegerField()
    question=models.CharField(max_length=250)
    is_enabled=models.IntegerField(default=1)



class apptitude_question(models.Model):
    class Meta:
        db_table:"apptitude_questions"
    
    questionType=models.CharField(max_length=250)
    questions=models.CharField(max_length=250)
    option1=models.CharField(max_length=250)
    option2=models.CharField(max_length=250)
    option3=models.CharField(max_length=250)
    option4=models.CharField(max_length=250)
    answer=models.CharField(max_length=250)
    
class personality_question(models.Model):
    class Meta:
        db_table:"personality_questions"
    
    question=models.CharField(max_length=250)
    openness_to_experience=models.CharField(max_length=250)
    conscientiousness=models.CharField(max_length=250)
    extraversion=models.CharField(max_length=250)
    agreeableness=models.CharField(max_length=250)
    neuroticism=models.CharField(max_length=250)

class job_details(models.Model):
    class Meta:
        db_table:"job_details"
    
    designation=models.CharField(max_length=250)
    salary=models.CharField(max_length=250)
    place=models.CharField(max_length=250)
    jobid=models.CharField(max_length=250)


    
