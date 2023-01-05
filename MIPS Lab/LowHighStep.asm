# Print number given an inputted range and step value.
# @author	Vienna Parnell
# @version	4.20.2022
.data
	askLow: 	.asciiz "Input a low number."
	askHigh: 	.asciiz "Input a high number."
	askStep: 	.asciiz "Input a step value."
	space: 		.asciiz " "
.text
.globl main
main:
	# Prints askLow prompt. 
	la $a0, askLow
	li $v0, 4
	syscall
	
	# Reads in inputted number, 
	li $v0, 5
	syscall 
	move $t0, $v0
	
	# Prints askHigh prompt. 
	la $a0, askHigh
	li $v0, 4
	syscall
	
	# Reads in inputted number, 
	li $v0, 5
	syscall 
	move $t1, $v0
	
	# Prints askStep prompt. 
	la $a0, askStep
	li $v0, 4
	syscall
	
	# Reads in inputted number, 
	li $v0, 5
	syscall 
	move $t2, $v0

loop:
	# End condition
	bgt $t0, $t1, endLoop # if low > high, end loop
	move $a0, $t0 # Moves number for printing
	li $v0, 1 # Prints number
	syscall 
	
	la $a0, space
	li $v0, 4
	syscall
	
	addu $t0, $t0, $t2
	j loop
	
endLoop:
	# Terminates program.
	li $v0, 10
	syscall