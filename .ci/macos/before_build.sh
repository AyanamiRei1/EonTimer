#!/bin/bash

source ~/venv3.8/bin/activate
pip3 install qtsass
qtsass -o resources/styles resources/styles
