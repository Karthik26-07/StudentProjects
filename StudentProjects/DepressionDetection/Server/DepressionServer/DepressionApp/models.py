from django.db import models

# Create your models here.
class accounts(models.Model):
    class Meta:
        managed = True
        db_table='account'

    name = models.CharField(max_length=30)
    username = models.CharField(max_length=30)
    password = models.CharField(max_length=50)
    email = models.CharField(max_length=30)
    phone = models.CharField(max_length=15)
    dateofbirth = models.CharField(max_length=15)


class questions(models.Model):
    class Meta:
        managed = True
        db_table='questions'

    question = models.CharField(max_length=255)
    option1 = models.CharField(max_length=255)
    option2 = models.CharField(max_length=255)
    option3 = models.CharField(max_length=255,default ='None')
    option4 = models.CharField(max_length=255,default = 'None')
    correct_option = models.CharField(max_length=255,default='None')
    category = models.CharField(max_length=30)
    is_enabled = models.IntegerField(default = 1)    


class custom_ans(models.Model):
    class Meta:
        managed = True
        db_table='temp_answer'

    Userid = models.IntegerField()
    Questionid = models.IntegerField()
    Answer = models.CharField(max_length=30)


class Progress_questions(models.Model):
    class Meta:
        managed = True
        db_table='progress_questions'

    Pr_question = models.CharField(max_length=255)
    Pr_option1 = models.CharField(max_length=255)
    Pr_option2 = models.CharField(max_length=255)
    Pr_option3 = models.CharField(max_length=255,default ='None')
    Pr_option4 = models.CharField(max_length=255,default = 'None')
    Pr_correct_option = models.CharField(max_length=255,default='None')
    Pr_category = models.CharField(max_length=30)
    Pr_is_enabled = models.IntegerField(default = 1)       


class Suggestions(models.Model):
    class Meta:
        managed = True
        db_table='suggetion'

    Suggestion = models.CharField(max_length=255)
    Title = models.CharField(max_length=255)

    is_enabled = models.IntegerField(default = 1)       

      
 