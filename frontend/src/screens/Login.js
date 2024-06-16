import React, { useState } from "react";
import { useNavigate } from 'react-router-dom';
import { TextField, Button, Card, CardContent, CardActions, CardMedia, Typography, IconButton, InputAdornment } from '@mui/material/';
import { styled } from '@mui/system';
import { Visibility, VisibilityOff } from '@mui/icons-material';
import { postLoginRegister } from '../adapters/OrdinaryAdapter';
import Config from '../config.json';

const RoundedTextField = styled(TextField)({
  '& .MuiOutlinedInput-root': {
    borderRadius: '20px',
    backgroundColor:'#0000008a',
    '&:hover fieldset': {
      borderColor: '#024caf', // Cor da borda ao passar o mouse
    },
    '&.Mui-focused fieldset': {
      borderColor: '#ffbd59', // Cor da borda quando focado
    },
    '&.Mui-error fieldset': {
      borderColor: '#f44336', // Cor da borda quando há um erro
    },
  },
  '& .MuiInputLabel-root': {
    '&.Mui-focused': {
      color: '#ffbd59', // Cor do rótulo quando focado
    },
    '&.Mui-error': {
      color: '#f44336', // Cor do rótulo quando há um erro
    },
  },
});

const RoundedCard = styled(Card)({
  backgroundColor: "#eeece1",
  borderRadius: '20px'
});

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    const requestBody = {
      "login": email,
      "password": password
    }

    try {
      const response = await postLoginRegister(Config.apiURL+'auth/login', requestBody)
      const { token } = response.data;
      localStorage.setItem("token", token);
      navigate('/Home');
    } catch (error) {
      setErrorMessage("Email ou senha inválidos");
    }
  };

  const handleClickShowPassword = () => {
    setShowPassword(!showPassword);
  };

  return (
    <div>       
      <div className="row">      
        <RoundedCard id="cardLogin" className="col-md-4 mx-auto my-5">
          <CardMedia
            component="img"
            className="mx-auto my-2"
            alt="logo taskflow"
            image="/logo2_taskflow.png"
            sx={{ height: 137, width: 154 }}
          />
          <CardContent>
            {errorMessage && <p>{errorMessage}</p>}         
            <form id="formLogin" onSubmit={handleSubmit}>
              <div className="form-group">
                <RoundedTextField 
                  id="tfEmail" 
                  label="Email" 
                  variant="outlined"
                  className="col-12"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  required 
                />
                <RoundedTextField
                  id="tfPassword"
                  label="Senha"
                  variant="outlined"
                  className="col-12"
                  type={showPassword ? 'text' : 'password'}
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  margin="dense"
                  required
                  //add olhinho para ver ou não a senha
                  InputProps={{
                    endAdornment: (
                      <InputAdornment position="end">
                        <IconButton
                          aria-label="toggle password visibility"
                          onClick={handleClickShowPassword}
                          edge="end"
                        >
                          {showPassword ? <VisibilityOff /> : <Visibility />}
                        </IconButton>
                      </InputAdornment>
                    )
                  }}
                />                  
              </div>
              <div id="divBtnLogin" className="d-flex justify-content-center">
                <Button 
                  id="btnLogin" 
                  className="col-3" 
                  type="submit" 
                  variant="contained"
                  sx={{
                    color: 'black',
                    backgroundColor: '#024caf',
                    margin: '10px', 
                    '&:hover': {
                      backgroundColor: '#00bf63', // Cor de fundo ao passar o mouse
                    },
                  }}
                >
                  Login
                </Button>            
              </div>
            </form>
          </CardContent>
          <CardActions>
            <div id="divRegistrar" className="col-12 d-flex justify-content-center">                  
              <Typography color={'black'} margin={'6px'}>Ainda não tem conta?</Typography>
              <Button 
                id="btnRegistrar" 
                variant="text"
                href="/auth/register"
                sx={{ color: '#002364' }}
              >
                Registre-se
              </Button>
            </div>
          </CardActions>
        </RoundedCard>
      </div>
    </div>
  );
}