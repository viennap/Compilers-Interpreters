# Calculates and prints the sum of two inputted numbers. 
# @author 	Vienna Parnell
# @version 	4.20.2022
.data
	ask: 	.asciiz "Input two numbers."
	return:	.asciiz "The sum is: "
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
	
	# Adds inputted numbers and prints sum. 
	addu $a0, $t0, $t1
	li $v0, 1
	syscall 
	
	# Terminates program. 
	li $v0, 10
	syscall