package in.bankingapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.bankingapp.dto.AccountDto;
import in.bankingapp.service.AccountService;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	private AccountService accountService;

	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}
	
	//Add Account Rest Api
	@PostMapping
	public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto){
		return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
	}
	
	//Get Account by id
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
		
		AccountDto accountDto =this.accountService.getAccountById(id);
			
		return ResponseEntity.ok(accountDto);
		
	}
	
	//deposit for Account
	@PutMapping("/{id}/deposit")
		public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request){
			Double amount = request.get("amount");
			AccountDto accountDto = this.accountService.deposit(id,amount );
			return ResponseEntity.ok(accountDto);
		}
	
	//withdraw for Account
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request){
		Double amount = request.get("amount");
		AccountDto accountDto = this.accountService.deposit(id,amount );
		return ResponseEntity.ok(accountDto);
	}
	
  //get all Account
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccount(){
		List<AccountDto> accounts = this.accountService.getAllAcount();
		
		return ResponseEntity.ok(accounts) ;
		
	}
	
	//delete Account Rest Api
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id){
		this.accountService.deleteById(id);
		return ResponseEntity.ok("Account is Deleted Successfully");
	}

}
