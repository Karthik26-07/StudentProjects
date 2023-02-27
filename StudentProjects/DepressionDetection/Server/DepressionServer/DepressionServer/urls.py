"""DepressionServer URL Configuration

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
from DepressionApp.views import *


urlpatterns = [
    path('admin/', admin.site.urls),
    path('register/', registerView),
    path('login/', loginView),
    path('index/', IndexView),
    path('Admin_login/',Admin_login),
    path('Depression_Question/',AddQuestion),
    path('Save_Questions/',SaveQuestionsView),
    path('get_Questions/',get_Questions),
    path('get_Answer/',get_Answer),
    path('progress_question/',Add_Progress_Question),
    path('Update_Depression_Button/',UpdateButtonDepression),
    path('AddProgressQuestion/',Add_ProgressQuestion),
    path('Update_Progress_Button/',UpdateButtonProgress),
    path('get_Progress_Question/',getProgressQuestion),
    path('get_Progress_Answer/',Progress_get_Answer),
    path('Suggetion/',Suggetion),
    path('Add_Suggetion/',Add_Suggetion),
    path('Update_Suggetion_Button/',UpdateButtonSuggetion),
    path('GetSuggestion/',Get_Suggetion),
    
    
    
    

]
