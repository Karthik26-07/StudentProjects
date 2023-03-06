"""CattleServer URL Configuration

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
from CattleServerApp.views import *

urlpatterns = [
    path('admin/', admin.site.urls),
    path('register/',registerView),
    path('login/',loginView),
    path('doctor/',doctorview),
    path('Admin_login/',Admin_login),
    path('',Adminpage),
    path('ViewDoctor/',Doctor_view),
    path('Updatebutton/',UpdateButton),
    path('AddDoctor/',AddDoctor),
    path('symptombased/',Symptoms),
    path('prediction/',predictionView),
    path('breedview/',breedsView),
    path('saveBreeds/',AddBreeds),
    path('get_breeds/',GetBreeds),

]
