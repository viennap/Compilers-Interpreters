# Calculates and prints the product of two inputted numbers. 
# @author	Vienna Parnell
# @version	4.20.2022
.data
	ask: 	.asciiz "Input a number. \n"
	return:	.asciiz "The number is:  "
	odd: 	.asciiz "Odd." 
	even: 	.asciiz "Even."
.text
.globl main
main:
	# Prints ask prompt. 
	la $a0, ask
	li $v0, 4
	syscall
	
	# Reads in inputted number, 
	li $v0, 5
	syscall 
	move $t2, $v0
	
	# Loads value of 2 into $t1.
	li $t3, 2
	
	div $t2, $t3
	mfhi $t4 # Calculates remainder.
	
	beq $t4, 1, isOdd # If there's a remainder, it's odd.
	la $a0, even # Else, it's even. 
	li $v0, 4
	syscall
	j end

isOdd:
	la $a0, odd # Loads in odd message and prints.
	li $v0, 4
	syscall
	j end
	
end:
	# Terminates program.
	li $v0, 10
	syscall
