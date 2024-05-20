import React, { useState } from "react";
import Config from '../config.json';
import Adapter, { post, get } from '../adapters/OrdinaryAdapter';
import { useNavigate } from "react-router-dom";
import {TextField, Button, Card, CardContent, CardMedia} from '@mui/material/';
import { styled } from '@mui/system';
import Navbar from '../components/Navbar.js'

export default function Tasks(){


    return (
        <div>
            {/* <h1>FOI</h1> */}
            <Navbar></Navbar>
        </div>
    )
}



