from django.http import HttpResponse
from django.shortcuts import render, redirect
from django.contrib import messages
from CattleServerApp.models import *
import json
import re
from django.views.decorators.csrf import csrf_exempt
import joblib as jb
from os import getcwd
import random
import base64
from PIL import Image
import io
from keras.preprocessing import image
from keras.models import load_model
import keras.utils as image
import numpy as np
from django import forms
from django.conf import settings
import os


symptoms = [
    'poor appetite',
    'trembling',
    'convulsion',
    'Respiratory distress',
    'Chronic coughing',
    'Stiffness to move',
    'muscel Twitching ',
    'Lockjaw',
    'stiff held out tail',
    'anxious',
    'Stillborn',
    'Weak calf',
    'infected membranes',
    'fetal membranes',
    'collapse',
    'stiff legs',
    'spasm',
    'paralysis',
    'weakness',
    'stagger',
    'abdominal pain',
    'Paleness',
    'Dark red urine',
    'Non-pigmented skin',
    'Hair loss',
    'peeling',
    'Crusting',
    'bleeding',
    'reddening',
    'fever',
    'mild fever'
    'nasal tissue',
    'salivation',
    'nasal discharge',
    'red nasal discharge',
    'musle movements',
    'Tremors',
    'dry lips',
    'smelly dug'
    'red urine',
    'black urine',
    'loos motion',
    'skin wound',
    'stright body hair',
    'no food intake',
    'Stumbling',
    'decreased milk production',
    'High temperature',
    'Coughing',
    'no chewing',
    'swallowing difficult',
    'Pneumonia',
    'Dull and depressed',
    'Raised breathing',
    'no food intake',
    'breathing difficult',
    'Anxiety',
    'Depression',
    'Infertility',
    'Abortion',
    'Respiratory distress',
    'Chronic coughing',
    'Weight loss',
    'nervous',
    'strange voice',
    'frequent sleeping',
    'enlarged udder',
    'filled stomach',
    'failed to stand',
    'soil licking',
    'Reduced appetite',
    'agression',
    'stomach pain',
    'mild cough',
    'yellow milk',
    'Reduced fertility',
    'Poor body condition',
    'high Pulse rate',
    'Lethargy',
    'excessive salivation',
    'swollen lips',
    'swollen tongue',
    'inflamed mouths',
    'lack of appetite',
    'swollen cheek',
    'no temperature',
    'foul-smelling mouth',
    'Swollen pharyngeal region',
    'ulcers on the tongue',
    'loss of hair',
    'skin damage',
    'white keratin',
    'scab',
    'shaking foot',
    'Bilsters in mouth',
    'Bilsters on mouth',
    'Quivering lips',
    'mouth frothing',
    'blisters on teats',
    'Lameness',
    'Rubbing',
    'Biting',
    'Scratching',
    'runny eyes',
    'red conjunctiva',
    'corneal ulcers',
    'itching',
    'bloody nasal discharge',
    'Jaundice',
    'abdomen swelling',
    'circular patch',
    'Grey skin',
    'Inability to eat',
    'Inability to drink',
    'Drooling',
    'loss of condition',
    'Painful tongue',
    'swollen tongue',
    'tongue Ulcer',
    'swallowing difficulty',
    'Seizures',
    'Unsteady gait',
    'wobbling',
    'falling over',
    'cold',
    'non-smelling diarrhoea',
    'sudden milk drop',
    'no musle movements',
    'Tremors',
    'Stumbling',
    'head down',
    'Unwillingness to move',
    'low temperature',
    'Reduced fertility',
]

diseaseList = [
    'Bluetongue',
    'Bovine Viral Diarrhoea(BVD)',
    'Calf Diphtheria',
    'Calf Pneumonia',
    'Fog Fever',
    'Infectious_Bovine_Rhinotracheitis(IBR)',
    'Bovine Tuberculosis',
    'Thrombosis',
    'Sleeping Disease',
    'Acetonaemia',
    'Fatty Liver',
    'Rumen Acidosis',
    'Bluetongue',
    'Bovine Anaemia',
    'intolerance',
    'Calf Diphtheria',
    'Digital Dermatitis',
    'Foot Rot',
    'Severe lameness',
    'holds leg up',
    'swollen interdigital space',
    'Anorexia',
    'Foot-and-Mouth',
    'Lice',
    'New Forest Eye',
    'Photosensitisation',
    'Pruritus',
    'Ragwort Poisoning',
    'Rain Scald',
    'Ringworm',
    'Sole Ulcer',
    'Wooden Tongue',
    'Rabies',
    'Cold Cow Syndrome',
    'Copper Poisoning',
    'Botulism',
    'Tetanus',
    'Thrombosis',
    'Bovine Viral Diarrhoea',
    'Brucellosis',
    'Anthrax',
]

# Create your views here.

def registerView(request):
    res = ""
    data1=[]
    default_image = "dairy.jpg"
    default = 0
    if request.method == 'GET':
        name = request.GET["name"]
        username = request.GET["username"]
        password = request.GET["password"]
        email = request.GET["email"]
        phone = request.GET["phone"]
        # name="kumar"
        # username="p347hh45"
        # password="1234"
        # email="1234"
        # phone="1234"

        if accounts.objects.filter(username=username).exists():
            res = "Username Already Exists"
            names={"message":res}
            data1.append(names)
        else:
            data = accounts(name=name, username=username,
                            password=password, email=email, phone=phone)
            data.save()
            res = "Account Created Successfully"
            Returndata={"message":res,"username":username,"password":password,"phone":phone}
            data1.append(Returndata)
    return HttpResponse(json.dumps(data1), content_type="application/json")


def loginView(request):
    res = ""
    if request.method == 'GET':
        contact = request.GET["username"]
        password = request.GET["password"]
        users = accounts.objects.filter(
            phone=contact, password=password).order_by('id')[:1]

        for user in users:
            if user.phone == contact:
                res = "success"
            else:
                res = "invalid"
    return HttpResponse(res)

def doctorview(request): 
    data=[]
    doctordata=doctor.objects.all().filter(is_enabled=1).values()
    data.extend(doctordata) 
    return HttpResponse(json.dumps(data), content_type="application/json")


def Adminpage(request):
      return render(request,'admin/AdminLogin.html')




def Admin_login(request):
    if request.method == 'POST':
        UserID = request.POST["userid"]
        Password = request.POST["password"]
        if UserID =="root" and Password =="root":
         return render(request,'Home/Home.html')
        else:
            messages.error(request,'Invalid login credentials')
            return render(request,'admin/AdminLogin.html')

def Doctor_view(request):
    doc=[]
    doctors=doctor.objects.all().values()
    doc.extend(doctors)
    # print(doc)
    return render(request,'Doctors/ViewDoctor.html',{'Doctors':doc})

def UpdateButton(request):
     if request.method == 'GET':
        Id = request.GET["Id"]
        flag = request.GET["flag"]
        print(Id,flag)
        doctor.objects.filter(id=Id).update(is_enabled=flag)
        return render(request,'Doctors/ViewDoctor.html')
    

def AddDoctor(request):
     if request.method == 'POST':
        Name=request.POST['name']
        Phone=request.POST['phone']
        Email=request.POST['email']
        Address=request.POST['address']
        doc=[]
       
        data=doctor(name=Name,contact=Phone,email=Email,address=Address)
        data.save()
        doctors=doctor.objects.all().values()
        doc.extend(doctors)
        
        print(data)
       
        return render(request,'Doctors/ViewDoctor.html',{'Doctors':doc})
    #  print("Reached")
    #  return render(request,'Doctors/ViewDoctor.html')
@csrf_exempt
def Symptoms(request):
    selectedList = request.POST.getlist('selectedList')
    model = jb.load(getcwd() + '\\trained_symtpms_model.h5')
    cow_symtoms = selectedList
    test_symptoms = []
    for x in range(0, len(symptoms)):
        test_symptoms.append(0)

    for k in range(0, len(symptoms)):

        for z in cow_symtoms:
            if (z == symptoms[k]):
                test_symptoms[k] = 1

    inputtest = [test_symptoms]

    predicted = model.predict(inputtest)
    print(diseaseList[predicted[0]])
    return HttpResponse(diseaseList[predicted[0]])


    #  return HttpResponse(json.dumps(data), content_type="application/json")
@csrf_exempt
def predictionView(request):
    prediction = ""
    if request.method == 'POST':
        simage = request.POST['image']
        filename = "Dairy-"+str(random.randint(1000000000, 9999999999))+".jpg"

        b = base64.b64decode(simage)
        img = Image.open(io.BytesIO(b))
        img.save('./media/prediction/'+filename)

        test_image = image.load_img(
            './media/prediction/'+filename, target_size=(64, 64))
        test_image = image.img_to_array(test_image)
        test_image = np.expand_dims(test_image, axis=0)

        classifier = load_model(getcwd() + '\\trained_cow_model.h5')
        result = classifier.predict(test_image)
        if result[0][0] == 1:
            prediction = 'Normal'
        else:
            prediction = 'Abnormal'

        print(prediction)

    return HttpResponse(prediction)
    

def breedsView(request):
    data=[]
    fooddata=food.objects.all().values()
    data.extend(fooddata) 

    return render(request,'admin/breeds.html',{'Food':data})

def AddBreeds(request):

   
    if request.method=="POST":
           
            data=[]
            fooddata=food.objects.all().values()
            data.extend(fooddata) 

            name = request.POST["name"]
            Description = request.POST["Description"]
            
            lifetime = request.POST["lifetime"]
            Origin = request.POST["Origin"]
            cost = request.POST["cost"]
            Image_File = request.FILES['images'].read()
            
           
            foodDetails = request.POST.getlist('foodDetails[]')
            my_string = ','.join(foodDetails)      
            
            print(my_string)
           
            img=bytearray(Image_File)
            a = base64.b64encode(img)
            filename = "Breed-"+str(random.randint(1000000000, 9999999999))+".jpg"

            b = base64.b64decode(img)
            with open('BreedImages/'+filename, 'wb') as f:
              f.write(base64.b64decode(a))
            # if breed.objects.filter(name=name).exists():


            Breed = breed(name=name, description=Description,
                            food_details=my_string, lifeTime=lifetime, cost=cost,origin=Origin,image=filename)
            Breed.save()  
            
            
            
            return render(request,'admin/breeds.html',{'Food':data})



@csrf_exempt
def GetBreeds(request):
    if request.method=="GET":
        data=[]
        Breed=[]
        breeds=breed.objects.all().values()
        for s in breeds:
            img=s['image']
            name=s['name']
            description=s['description']
            food_details=s['food_details']
            lifeTime=s['lifeTime']
            cost=s['cost']
            origin=s['origin']
            id=s['id']
            with open('BreedImages/'+img, "rb") as image:
              f = image.read()
              b = bytearray(f)
        
              a = base64.b64encode(b)
        
              output = a.decode()
              dataSet={
               'Image':output,
               'Name':name,
               'Description':description,
               'Food':food_details,
               'Lifetime':lifeTime,
               'Cost':cost,
               'Origin':origin,
               'ID':id
              }
            data.append(dataSet)
        Breed.extend(data)

        

    return HttpResponse(json.dumps(Breed), content_type="application/json")
