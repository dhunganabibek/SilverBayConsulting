import React,{useContext} from 'react'
import { AppContext } from '../context/AppContext'

function Header() {
    const phoneNumber = useContext(AppContext);
  return (

    <div>{phoneNumber}</div>
  )
}

export default Header