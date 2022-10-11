package project.trut.web.Tour;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.trut.domain.api.TourApiDto;
import project.trut.service.tour.TourApiService;
import project.trut.domain.tour.InitTour;
import project.trut.domain.tour.TourLocalRepository;

import java.util.*;

@Controller
@Slf4j
@RequestMapping("/trut/tour")
public class TourController {

    private final TourApiService tourApiService;
    private final List<TourApiDto> tourList;

    public TourController(TourApiService tourApiService, TourLocalRepository tourLocalRepository) {
        this.tourApiService = tourApiService;
        this.tourList = tourLocalRepository.getTourList();
    }

    @ModelAttribute("initTours")
    public InitTour[] initTours(){
        return InitTour.values();
    }


    @GetMapping
    public String wayPoint(@RequestParam(name = "parse", defaultValue = "false") boolean parse,
                           @RequestParam(name="size", defaultValue = "false") boolean size,
                           @RequestParam(name="select", defaultValue = "false") boolean select,
                           @RequestParam(name="del", defaultValue = "false") boolean del,
                           Model model) {


        model.addAttribute("paging", new TourPaging());
        model.addAttribute("tourList", new ArrayList<TourApiDto>());
        model.addAttribute("classification", InitTour.nature);

        return "trut/addTour";
    }

    @PostMapping
    public String findTour(@ModelAttribute("classification") InitTour initTour,
                           Model model,
                           RedirectAttributes redirectAttributes) {

        TourPaging paging = new TourPaging();
        paging.setCode(initTour.getCode());
        try {
            getTourList(model, paging);
        } catch (ParseException e) {
            redirectAttributes.addAttribute("parse", true);
            return "redirect:/trut/tour";
        }

        return "trut/addTour";
    }

    @RequestMapping("/{pageNum}")
    public String tourPage(@PathVariable("pageNum") int pageNum,
                           @RequestParam(name = "classification", required = false) Optional<InitTour> initTour,
                           @RequestParam(name = "code", required = false) Optional<String> code,
                           Model model,
                           RedirectAttributes redirectAttributes) {


        if (!initTour.isPresent()) {
            for (InitTour tour: InitTour.values()) {
                if (tour.getCode().equals(code.get())) {
                    initTour = Optional.of(tour);
                }
            }
        }

        TourPaging paging = new TourPaging();
        paging.setCode(initTour.get().getCode());
        paging.setPageNum(pageNum);

        try {
            getTourList(model, paging);
        } catch (ParseException e) {
            redirectAttributes.addAttribute("error", true);
            return "redirect:/trut/tour";
        }

        model.addAttribute("classification", initTour.get());

        return "trut/addTour";
    }

    @PostMapping("/add")
    public String addTour(@ModelAttribute("tour") TourApiDto tour,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        if (tourList.size() >= 3) {
            redirectAttributes.addAttribute("size", true);
            return "redirect:/trut/tour";
        }

        tourList.add(tour);
        redirectAttributes.addAttribute("select", true);
        return "redirect:/trut/tour";
    }

    @GetMapping("/edit")
    public String editForm(Model model) {
        model.addAttribute("tourList", tourList);
        return "/trut/editTour";
    }

    @PostMapping("/edit")
    public String editTour(@ModelAttribute("tour") TourApiDto tour,
                           Model model) {

        log.info("tour = {}", tour.toString());

        for (int i=0; i < tourList.size(); i++) {
            if (tourList.get(i).equals(tour)) {
                tourList.remove(i);
            }
        }

        return "redirect:/trut/tour/edit";
    }

    @GetMapping("/del")
    public String delTour(Model model, RedirectAttributes redirectAttributes) {

        tourList.clear();

        redirectAttributes.addAttribute("del", true);
        return "redirect:/trut/tour";
    }


    private void getTourList(Model model, TourPaging paging) throws ParseException {
        List<TourApiDto> tourList = tourApiService.getTourList(paging);
        model.addAttribute("tourList", tourList);
        model.addAttribute("paging", paging);
    }
}
