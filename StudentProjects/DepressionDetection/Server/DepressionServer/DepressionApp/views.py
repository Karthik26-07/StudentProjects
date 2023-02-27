from django.shortcuts import render
from DepressionApp.models import *
from django.http import HttpResponse
import json
from django.contrib import messages
import sweetify
from flask import Flask, session
from django.views.decorators.csrf import csrf_exempt
from datetime import date,datetime

 

# Create your views here.
def registerView(request):
    res = ""
    # default_image = "dairy.jpg"
    default = 0
    if request.method == 'POST':
        name = request.POST["name"]
        username = request.POST["username"]
        password = request.POST["password"]
        email = request.POST["email"]
        phone = request.POST["phone"]
        dateofbirth = request.POST["date"]
       
        x = dateofbirth.split("- ")
        year = x[0]
        month = x[1]
        day = x[2]
        today = date.today()
        
        age = today.year - int(year) - ((today.month, today.day) < (int(month),int(day)))

        print(type(age))
        
        if (age <= 15):
               res = "Not elligible to use this application"
               
        
        elif accounts.objects.filter(username=username).exists():
                 res = "Username Already Exists"
                
        
        elif accounts.objects.filter( email=email ).exists():
                res = "Email id Already Exists"
            
      
        else:
            data = accounts(name=name, username=username,
                            password=password, email=email, phone=phone,dateofbirth=dateofbirth)
            data.save()
    
            res = "Account Created Successfully"
           
    return HttpResponse(res)

@csrf_exempt
def loginView(request):
    res = ""
    data=[]
    if request.method == 'GET':
        email= request.GET["username"]
        password = request.GET["password"]
        users = accounts.objects.filter(
            email=email, password=password).order_by('id')[:1]
        
        if not users:
             res = "invalid"
             names={"response":res}
             data.append(names)
        else:
              for user in users:
                 ids=user.id
                #  request.session['id'] =user.id
                #  request.session.modified=True
                #  request.session.save
                #  print(request.session['id'])

            

                 res = "valid"
                 names={"response":res ,"id":ids}
                 data.append(names)

    return HttpResponse(json.dumps(data), content_type="application/json")

def IndexView(request):
      return render(request,'index.html')

def Admin_login(request):
    if request.method == 'POST':
        UserID = request.POST["userid"]
        Password = request.POST["password"]
        if UserID =="Admin" and Password =="Admin":
         return render(request,'Home/Home.html')
        else:
            messages.error(request,'Invalid login credentials')
            return render(request,'index.html')

def AddQuestion(request):
    data=questions.objects.all().values()
    print(data)
    
    return render(request,'Home/AddDepressionQuestion.html',{'Questions':data})

def SaveQuestionsView(request):
    if request.method == 'POST':
         Question = request.POST["Question"]
         option1 = request.POST["option1"]
         option2 = request.POST["option2"]
        #  option3 = request.POST["option3"]
        #  option4 = request.POST["option4"]
        #  Answer = request.POST["correctAns"]
         category=request.POST["selectcategory"]
        #  form = NewRegistration()
        #  if form.va



         data = questions(question=Question, option1=option1,
                            option2=option2,category=category)
            
         data.save()
         questionsData=questions.objects.all().values()

         sweetify.success(request, 'Added', text='Successfully Added ', persistent='OK')
                

                # messages.error(request,'Successfully Added')    
    

    return render(request,'Home/AddDepressionQuestion.html',{'Questions':questionsData} )

def get_Questions(request): 

    if request.method == 'GET':
        category=""
        userIds=request.GET["userId"]
        Questions_data=[]
       
        users = accounts.objects.filter(
            id=userIds).values()
       
        for data in users:
            date=data['dateofbirth']
        
        x = str(date).split("- ")
        year = x[0]
        month = x[1]
        day = x[2]
        print(datetime.today().strftime('%Y-%m-%d')
) 
        today =datetime.today()
        age = today.year - int(year) - ((today.month, today.day) < (int(month),int(day)))
        print(age)
        
        if (age >15 & age <25):
            category="Teenager"
        elif (age >25 & age <60) :
            category="Adult"
        else:
             category="Senior Citizen"
       
        question=questions.objects.all().filter(is_enabled=1).values()
        Questions_data.extend(question)
        # print(Questions_data)
    
    return HttpResponse(json.dumps(Questions_data), content_type="application/json")

def get_Answer(request):
    if request.method =='GET':
        Answers=request.GET['Answers']   
        data=[]
        
        res = "Under Proccessing.."
        Returndata={"response":res}
        data.append(Returndata)
    return HttpResponse(json.dumps(data), content_type="application/json")
    
def Add_Progress_Question(request):
     data=Progress_questions.objects.all().values()


     return render(request,'Home/AddProgressQuestion.html',{'Questions':data}  )

@csrf_exempt
def UpdateButtonDepression(request):
     if request.method == 'POST':
        Id = request.POST["Id"]
        flag = request.POST["flag"]
        print(Id,flag)
        questions.objects.filter(id=Id).update(is_enabled=flag)
        return render(request,'Home/AddDepressionQuestion.html')
    

def Add_ProgressQuestion(request):  
      if request.method == 'POST':
         Question = request.POST["Question"]
         option1 = request.POST["option1"]
         option2 = request.POST["option2"]
        #  option3 = request.POST["option3"]
        #  option4 = request.POST["option4"]
        #  Answer = request.POST["correctAns"]
         category=request.POST["selectcategory"]
       
         data = Progress_questions(Pr_question=Question, Pr_option1=option1,
                            Pr_option2=option2,Pr_category=category)
            
         data.save()
         questionsData=Progress_questions.objects.all().values()

         sweetify.success(request, 'Added', text='Successfully Added ', persistent='OK')
                

                # messages.error(request,'Successfully Added')    
      return render(request,'Home/AddProgressQuestion.html',{'Questions':questionsData} )


@csrf_exempt
def UpdateButtonProgress(request):
      if request.method == 'POST':
        Id = request.POST["Id"]
        flag = request.POST["flag"]
        print(Id,flag)
        Progress_questions.objects.filter(id=Id).update(Pr_is_enabled=flag)
        return render(request,'Home/AddProgressQuestion.html')      

def getProgressQuestion(request):
      
     if request.method == 'GET':
        category=""
        userIds=request.GET["userId"]
        Questions_data=[]
       
        users = accounts.objects.filter(
            id=userIds).values()
       
        for data in users:
            date=data['dateofbirth']
        
        x = str(date).split("- ")
        year = x[0]
        month = x[1]
        day = x[2]
        print(datetime.today().strftime('%Y-%m-%d')
) 
        today =datetime.today()
        age = today.year - int(year) - ((today.month, today.day) < (int(month),int(day)))
        print(age)
        
        if (age >15 & age <25):
            category="Teenager"
        elif (age >25 & age <60) :
            category="Adult"
        else:
             category="Senior Citizen"
       
        question=Progress_questions.objects.all().filter(Pr_is_enabled=1).values()
        Questions_data.extend(question)
        # print(Questions_data)
    
     return HttpResponse(json.dumps(Questions_data), content_type="application/json")

def Progress_get_Answer(request):
    if request.method =='GET':
        Answers=request.GET['Progress_Answers']   
        data=[]
        
        res = "Under Proccessing.."
        Returndata={"response":res}
        data.append(Returndata)
    return HttpResponse(json.dumps(data), content_type="application/json")


def Suggetion(request):
    suggetions=Suggestions.objects.all().values()

    return render(request,'Home/Suggetion.html',{'Suggetions':suggetions})       


def Add_Suggetion(request):
    if request.method =='POST':
        Suggestion=request.POST['suggetion']
        Title=request.POST['title']
        data = Suggestions(Suggestion=Suggestion,Title=Title)
            
        data.save()
        suggetions=Suggestions.objects.all().values()

        sweetify.success(request, 'Added', text='Successfully Added ', persistent='OK')
                
    return render(request,'Home/Suggetion.html',{'Suggetions':suggetions})      

@csrf_exempt
def UpdateButtonSuggetion(request):
      if request.method == 'POST':
        Id = request.POST["Id"]
        flag = request.POST["flag"]
        print(Id,flag)
        Suggestions.objects.filter(id=Id).update(is_enabled=flag)
        return render(request,'Home/Suggetion.html') 

def Get_Suggetion(request):
    if request.method=='GET':
         data=[]
         suggetions=Suggestions.objects.all().filter(is_enabled=1).values()
         data.extend(suggetions)
    
    return HttpResponse(json.dumps(data), content_type="application/json")
     




