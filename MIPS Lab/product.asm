# Calculates and prints the product of two inputted numbers. 
# @author	Vienna Parnell
# @version	4.20.2022
.data
	ask: 	.asciiz "Input two numbers."
	return:	.asciiz "The product is: "
.text
.globl main
main:
	# Prints ask prompt. 
	la $a0, ask
	li $v0, 4
	syscall
	
	# Reads in first inputted number, 
	li $v0, 5
	syscall 
	move $t0, $v0 
	
	# Reads in second inputted number. 
	li $v0, 5
	syscall
	move $t1, $v0 
	
	# Prints return phrase.
	la $a0, return
	li $v0, 4
	syscall
	
	# Calculates and prints product of inputted numbers. 
	mult $t0, $t1
	mflo $a0
	li $v0, 1
	syscall
	
	# Terminates program.
	li $v0, 10
	syscall

	
	
	