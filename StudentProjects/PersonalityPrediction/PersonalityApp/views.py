from django.shortcuts import render
from PersonalityApp.models import *
from django.db.models import Q
import sweetify
from django.http import HttpResponse
import json


# Create your views here.


def index(request):
    return render(request,'index.html')

def user(request):
    return render(request,'user/index.html')


def registration(request):
    return render(request,'user/registration.html')


def saveUser(request):
    if request.method == 'POST':
        farmername = request.POST['uname']
        contactNo = request.POST['contactNo']
        emailId = request.POST['emailId']
        address = request.POST['address']
        username = request.POST['username']
        password = request.POST['password']

        user = User.objects.filter(
            Q(email=emailId) | Q(contact=contactNo) | Q(user_name=username)
        ).first()

        has_error = False
        error = ''

        if user != None and user.user_name == username:
            has_error = True
            error = 'Duplicate user name'

        if user != None and user.email == emailId:
            has_error = True
            error = 'Duplicate email'

        if user != None and user.contact == contactNo:
            has_error = True
            error = 'Duplicate contact number'

        if has_error:
            return render(request, "user/registration.html", {'error': error})

        user = User(name=farmername, contact=contactNo, email=emailId,
                    address=address, user_name=username, password=password)
        user.save()

        return render(request, "user/registration.html", {'success': 'User Added Successfully'})
    else:
        return render(request, 'user/registration.html')


def userlogin(request):
    if request.method == 'POST':
        username = request.POST['username']
        password = request.POST['password']
        user = User.objects.values_list('password', 'id', 'name').\
            filter(user_name=request.POST['username'])

        user = User.objects.filter(
            user_name=username, password=password).first()

        if user == None:
            return render(request, 'user/index.html', {'error': 'Invalid login credentials'})

        request.session['userid'] = user.id
        request.session['userName'] = user.name

        return render(request, 'user/userHome.html')

    else:
        return render(request, 'user/index.html')


def adminlogin(request):
    return render(request,'admin/login.html')

def adminloginview(request):
    if request.method == "POST":
        adminusername=request.POST["username"]
        adminpass=request.POST["password"]

        if adminusername == 'admin' and adminpass == 'admin':
            return render(request,'admin/home.html')
        else:
            return render(request, 'admin/login.html', {'error': 'Invalid login credentials'})

def Apptitude(request):
    return render(request,'admin/AptitudeQuestions.html')

def Add_Apptitude(request):
    if request.method=="POST":
         Question = request.POST["Question"]
         option1 = request.POST["option1"]
         option2 = request.POST["option2"]
         option3 = request.POST["option3"]
         option4 = request.POST["option4"]
         Answer = request.POST["correctAns"]
         Type=request.POST["type"]
         data=apptitude_question(questionType=Type,questions=Question,
         option1=option1,option2=option2,option3=option3,option4=option4,answer=Answer)
         data.save()
         sweetify.success(request, 'Added', text='Successfully Added ', persistent='OK')
    return render(request,'admin/AptitudeQuestions.html')
      
def Pesonality(request):
    return render(request,'admin/PersonalityQuestion.html')

def Add_Pesonality(request):
    if request.method=="POST":
         question = request.POST["Question"]
         openness_to_experience = request.POST["Openness"]
         conscientiousness = request.POST["Conscientiousness"]
         extraversion = request.POST["Extraversion"]
         agreeableness = request.POST["Agreebleness"]
         neuroticism = request.POST["Neuroticism"]
       
         data=personality_question(question=question,openness_to_experience=openness_to_experience,
         conscientiousness=conscientiousness,extraversion=extraversion,
         agreeableness=agreeableness,neuroticism=neuroticism)
         data.save()
         sweetify.success(request, 'Added', text='Successfully Added ', persistent='OK')
    return render(request,'admin/PersonalityQuestion.html')


def displayAptitude(request):
    aptitude=apptitude_question.objects.all().values()
    return render(request,'admin/DisplayAptitudeQuestion.html',{'Aptitude':aptitude}) 

def DisplayPersonality(request):
    personality=personality_question.objects.all().values()

    return render(request,'admin/DisplayPersonalityQuestion.html',{'Personality':personality}) 

def personalityTest(request):
      aptitude=apptitude_question.objects.all().values()
      personality=personality_question.objects.all().values()
      return render(request, 'user/personalityTest.html',{'Personality':personality,'Aptitude':aptitude})

def aptitudeTest(request):
      aptitude=apptitude_question.objects.all().values()
      personality=personality_question.objects.all().values()
      return render(request, 'user/aptitudeTest.html',{'Personality':personality,'Aptitude':aptitude})


def Add_Job(request):
    
    return render(request,'admin/jobDetails.html') 

def Save_Job(request):
      if request.method=="POST":
        
         Designation = request.POST["designation"]
         Place = request.POST["salary"]
         Salary = request.POST["place"]
         jobid = request.POST["jobid"]
         data=job_details(jobid=jobid,designation=Designation,salary=Salary,place=Place)
         data.save()
         sweetify.success(request, 'Added', text='Successfully Added ', persistent='OK')
   
      return render(request,'admin/jobDetails.html') 

def jobRequirement(request):
    
    data=job_details.objects.all().values()
    return render(request,'admin/jobRequirement.html',{'jobdata':data}) 

def jobdetails(request):
    if request.method=="GET":
        data=[] 
        ids=request.GET["id"]
        jobdata=job_details.objects.all().filter(id=ids).values()
        data.extend(jobdata)
        print(data)
 
    return HttpResponse(json.dumps(data), content_type="application/json")
    
