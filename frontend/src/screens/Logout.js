import React from "react";
import { useNavigate } from 'react-router-dom';
import { Button, Card, CardContent, CardMedia, Typography } from '@mui/material/';
import { HttpAuthToken } from "../http";

export default function Logout() {
  useNavigate();
  HttpAuthToken.deleteToken();

  return (
    <div>
      <div className="row">
        <Card id="cardLogin" className="col-md-4 mx-auto my-5">
          <CardMedia
            component="img"
            className="mx-auto my-2"
            alt="logo taskflow"
            image="/logo2_taskflow.png"
            sx={{ height: 100, width: "auto" }}
          />
          <Typography textAlign="center">Agradecemos por usar <b>TaskFlow!</b></Typography>
          <CardContent>
            <div id="btnRegistrar" className="d-flex justify-content-center">
              <Button
                id="btnRegistrar"
                variant="contained"
                href="/auth/login"
              >
                Login
              </Button>
            </div>
          </CardContent>
        </Card>
      </div>
    </div>
  );
}
