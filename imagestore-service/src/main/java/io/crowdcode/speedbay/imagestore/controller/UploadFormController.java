package io.crowdcode.speedbay.imagestore.controller;

import io.crowdcode.speedbay.imagestore.service.ImageStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Controller
public class UploadFormController {

    @Autowired
    private ImageStoreService imageStoreService;

    @RequestMapping(path = "/upload/{ownerUuid}", method = RequestMethod.GET)
    public String provideUploadInfo(@PathVariable("ownerUuid") String ownerUuid, Model model) {
        model.addAttribute("hashes", imageStoreService.listHashes(ownerUuid));
        model.addAttribute("ownerUuid", ownerUuid);
        return "uploadForm";
    }


}
