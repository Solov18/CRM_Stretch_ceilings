package MyCeiling_.task_list.task_list.controller;

import MyCeiling_.task_list.task_list.domain.Sale;
import MyCeiling_.task_list.task_list.service.CustomerService;
import MyCeiling_.task_list.task_list.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sales")
public class SaleController {
    private final SaleService saleService;
    private final CustomerService customerService;

    @Autowired
    public SaleController(SaleService saleService, CustomerService customerService) {
        this.saleService = saleService;
        this.customerService = customerService;
    }

    @GetMapping
    public String getAllSales(Model model) {
        List<Sale> sales = saleService.findAll(); // Получение всех продаж из сервиса или репозитория
        model.addAttribute("sales", sales);
        return "sales"; // Возвращаем имя шаблона Thymeleaf
    }

    @GetMapping("/{id}")
    public String getSaleById(@PathVariable Long id, Model model) {
        Optional<Sale> sale = saleService.findById(id);
        if (sale.isPresent()) {
            model.addAttribute("sale", sale.get());
            return "sale_detail";
        } else {
            return "redirect:/sales";
        }
    }

    @GetMapping("/new")
    public String showCreateSaleForm(Model model) {
        model.addAttribute("sale", new Sale());
        model.addAttribute("customers", customerService.findAll());
        return "new_sale";
    }

    @PostMapping
    public String createSale(@ModelAttribute Sale sale) {
        saleService.save(sale);
        return "redirect:/sales";
    }

    @GetMapping("/edit/{id}")
    public String showEditSaleForm(@PathVariable Long id, Model model) {
        Optional<Sale> sale = saleService.findById(id);
        if (sale.isPresent()) {
            model.addAttribute("sale", sale.get());
            model.addAttribute("customers", customerService.findAll());
            return "edit_sale";
        } else {
            return "redirect:/sales";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateSale(@PathVariable Long id, @ModelAttribute Sale saleDetails) {
        Optional<Sale> optionalSale = saleService.findById(id);
        if (optionalSale.isPresent()) {
            Sale existingSale = optionalSale.get();
            existingSale.setProduct(saleDetails.getProduct());
            existingSale.setAmount(saleDetails.getAmount());
            existingSale.setSaleDate(saleDetails.getSaleDate());
            existingSale.setCustomer(saleDetails.getCustomer());
            saleService.save(existingSale);
            return "redirect:/sales";
        } else {
            // Handle case where sale with given ID is not found
            return "redirect:/sales";
        }
    }



    @GetMapping("/delete/{id}")
    public String deleteSale(@PathVariable Long id) {
        if (saleService.findById(id).isPresent()) {
            saleService.deleteById(id);
        }
        return "redirect:/sales";
    }

    @GetMapping("/details/{id}")
    public String getSaleDetails(@PathVariable("id") Long id, Model model) {
        Sale sale = saleService.getSaleById(id);
        model.addAttribute("sale", sale);
        return "sale-details";
    }
}