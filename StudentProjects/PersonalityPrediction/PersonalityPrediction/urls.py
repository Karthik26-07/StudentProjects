"""PersonalityPrediction URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/3.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from PersonalityApp.views import *

urlpatterns = [
    path('admin/', adminlogin),
    path('',index),
    path('user/',user),
    path('registration/',registration),
    path('saveUser/',saveUser),
    path('userlogin/',userlogin),
    path('logout/',index),
    path('adminlogin/',adminloginview),
    path('apptitude/',Apptitude),
    path('Add_aptitude_question/',Add_Apptitude),
    path('personality/',Pesonality),
    path('Add_personality_question/',Add_Pesonality),
    path('displayAptitude/',displayAptitude),
    path('displayPersonality/',DisplayPersonality),
    path('test/',personalityTest),
    path('aptitudetest/',aptitudeTest),
    path('Add_job_details/',Add_Job),
    path('Save_job_details/',Save_Job),
    path('job_requirement/',jobRequirement),
    path('jobdetails/',jobdetails),
    path('Add_Job_Requirement/',Add_Job_Requirement),
    path('displayJob/',displayJobDetail),
    path('preferedCVs/',UploadPreferedCV),
    path('editPersonalityQustion/',EditPersonality),
    path('editVIEW/',EditViewDetails),
    path('updatepersonalityVIEW/',UpdatePersonality),
    path('deletepersonalityVIEW/',DeletePersonality),
    path('updateaptitudeVIEW/',UpdateAptitude),
    path('deleteaptitudeVIEW/',DeleteAptitude),
    path('updatejobdetailVIEW/',UpdateJobDetail),
    path('deletejobdetailVIEW/',DeleteJobDetail),
]
