package amadeuszx.petclinic.springpetclinic.formatters;


import amadeuszx.petclinic.springpetclinic.model.PetType;
import amadeuszx.petclinic.springpetclinic.services.PetTypeService;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class PetTypeFormatter implements Formatter<PetType> {

  private final PetTypeService petTypeService;

  public PetTypeFormatter(final PetTypeService petTypeService) {this.petTypeService = petTypeService;}

  @Override
  public String print(PetType petType, Locale locale) {
    return petType.getName();
  }

  @Override
  public PetType parse(String text, Locale locale) throws ParseException {
    return petTypeService.findAll().stream().filter(p -> p.getName().equals(text)).findFirst()
        .orElseThrow(() -> new ParseException("type not found" + text, 0));
  }
}
