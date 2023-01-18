package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services.interfaces;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.dto.ReqAuthDto;

public interface AuthServive {
    public String logIn(ReqAuthDto authDto);

    public void signUp(ReqAuthDto playerDto);
}
