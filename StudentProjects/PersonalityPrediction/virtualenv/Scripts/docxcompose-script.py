#!d:\projects(22-23)\personalityprediction\virtualenv\scripts\python.exe
# EASY-INSTALL-ENTRY-SCRIPT: 'docxcompose==1.4.0','console_scripts','docxcompose'
__requires__ = 'docxcompose==1.4.0'
import re
import sys
from pkg_resources import load_entry_point

if __name__ == '__main__':
    sys.argv[0] = re.sub(r'(-script\.pyw?|\.exe)?$', '', sys.argv[0])
    sys.exit(
        load_entry_point('docxcompose==1.4.0', 'console_scripts', 'docxcompose')()
    )
