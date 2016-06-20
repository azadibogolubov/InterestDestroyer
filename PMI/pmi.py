amount = input('Enter loan amount after down payment: $')
down_payment = input('Enter down payment percent: ')

if down_payment >= 5 and down_payment < 10:
	pmi = amount * .0078
elif down_payment >= 10 and down_payment < 15:
	pmi = amount * .0052
elif down_payment > 15 and down_payment < 20:
	pmi = amount * .0032
else:
	pmi = 0
print('PMI is: ' + str(pmi / 12))

