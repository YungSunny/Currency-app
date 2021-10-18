import React, {useState, useEffect} from 'react'
import Styled from 'styled-components';
import BankLogo from '../resources/bank-icon.png';
import TableCom from '../components/Table'
import "react-datepicker/dist/react-datepicker.css";
import Converter from '../components/Converter'
import "react-loader-spinner/dist/loader/css/react-spinner-loader.css"
import Loader from 'react-loader-spinner'

const Currency = () => {

    const [currencyData, setCurrencyData] = useState([]);
    const [currencyCodes, setCurrencyCodes] = useState([]);
    const [loader, setLoader] = useState(false);

    const fetchCurrencies = async () => {
        setLoader(true);
        const response = await fetch("http://localhost:8080/currency/api/get/all");
        const result = await response.json();
        setCurrencyData(result);
        const codes = (result.map(item =>{
            return(
                item.currencyCode
            )
        }))
        codes.push("EUR")
        setCurrencyCodes(codes)
        setLoader(false)
      }

      useEffect(() => {
        fetchCurrencies();
      }, []);

    
    return (
        <div>
            <LogoWrapper>
                <a href="/">
                    <Logo src={BankLogo}/>
                </a>
            </LogoWrapper>
            {loader ? 
                <Loading
                    color="#FFFFF"
                    height={100}
                    width={100}
                /> :
                <InputWrapper>
                    <Converter
                        codes={currencyCodes}
                        data={currencyData}
                    />
                </InputWrapper>
            }
            <TableCom data={currencyData} />
        </div>
    )
}

export default Currency;

const LogoWrapper = Styled.div`
    width: 120px;
    margin: 0 auto;
`
const Logo = Styled.img`
    width: 100%;
    text-align: center;
`;

const InputWrapper = Styled.div`
    text-align: center;
    margin: 20px auto;
`
const Loading = Styled(Loader)`
    position: absolute;
    top: 50%;
    left: 50%;
`
