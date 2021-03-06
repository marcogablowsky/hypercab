package de.mag.hypercab.web;

import java.io.IOException;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import de.mag.hypercab.app.vpinmame.Rom;
import de.mag.hypercab.app.vpinmame.VPinMameService;

@Controller
@RequestMapping("/vpinmame")
public class VPinMameController {

	@Resource
	private VPinMameService vPinMameService;

	@ResponseBody
	@RequestMapping(value = "/roms", method = RequestMethod.GET)
	public Set<Rom> getRoms() {
		return vPinMameService.getInstalledRoms();
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/roms/{romName}", method = RequestMethod.DELETE)
	public void deleteRom(@PathVariable String romName) {
		vPinMameService.deleteRom(romName);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/roms/{romName}", method = RequestMethod.PUT)
	public void updateRomSettings(@RequestBody Rom rom) throws IOException {
		vPinMameService.updateRomSettings(rom);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/roms", method = RequestMethod.POST)
	public void storeRomFile(@RequestParam("file") MultipartFile file) throws IOException {
		vPinMameService.storeRomFile(file.getInputStream(), file.getOriginalFilename());
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/registry", method = RequestMethod.PUT)
	public void writeRegistry() throws IOException {
		vPinMameService.updateRegistry();
	}

}
