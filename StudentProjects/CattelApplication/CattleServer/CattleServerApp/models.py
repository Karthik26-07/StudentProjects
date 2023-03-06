from django.db import models

# Create your models here.

class accounts(models.Model):
    class Meta:
        db_table='account'

    name = models.CharField(max_length=30)
    username = models.CharField(max_length=30)
    password = models.CharField(max_length=50)
    email = models.CharField(max_length=30)
    phone = models.CharField(max_length=15)

class doctor(models.Model):
    class Meta:
        db_table='doctor'

    name = models.CharField(max_length=30)
    contact = models.CharField(max_length=30)
    email = models.CharField(max_length=30)
    address = models.CharField(max_length=150) 
    is_enabled = models.IntegerField(default = 0)    


class food(models.Model):
    class Meta:
        db_table='food_detals'

    foodName = models.CharField(max_length=255)
     
class breed(models.Model):
    class Meta:
        db_table='breeds'

    name = models.CharField(max_length=50)
    description = models.CharField(max_length=255)
    food_details = models.CharField(max_length=255)
    lifeTime = models.CharField(max_length=50)
    cost = models.CharField(max_length=150) 
    origin = models.CharField(max_length=50) 
    image = models.CharField(max_length=150) 
    is_enabled = models.IntegerField(default = 0)  

 
     