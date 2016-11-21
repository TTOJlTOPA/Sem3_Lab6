package by.sem3.lab6_7;

import by.sem3.util.FormatJSON;
import by.sem3.util.FormatXML;
import by.sem3.util.IncorrectFormatException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

class Companies {
    private final List<Company> companies;

    public Companies(List<String[]> list) throws CompaniesIsEmptyException {
        this.companies = new ArrayList<>();
        Iterator<String[]> iterator = list.iterator();
        iterator.next();
        while (iterator.hasNext()) {
            companies.add(new Company(iterator.next()));
        }
        setColumnsNames(list.get(0));
    }

    public void print() throws CompaniesIsEmptyException, IncorrectColumnsException {
        if (!isEmpty()) {
            CompanyUtil.printColumnsNames();
            companies.forEach(CompanyUtil::print);
        } else {
            throw new CompaniesIsEmptyException();
        }
    }

    public Company findByShortTitle() throws CompaniesIsEmptyException {
        if (!isEmpty()) {
            Scanner scan = new Scanner(System.in);
            String title;
            System.out.print("Enter short title: ");
            title = scan.nextLine();
            for (Company iterator : companies) {
                if (title.equalsIgnoreCase(iterator.getShortTitle())) {
                    return iterator;
                }
            }
            System.out.println("Not found.");
            return null;
        } else {
            throw new CompaniesIsEmptyException();
        }
    }

    public Stream<Company> filterByBranch(String inBranch) throws CompaniesIsEmptyException {
        if (!isEmpty()) {
            Stream<Company> selection;
            selection = (companies.stream()).filter(company -> (company.getBranch()).equalsIgnoreCase(inBranch));
            return selection;
        } else {
            throw new CompaniesIsEmptyException();
        }
    }

    public Stream<Company> filterByActivity(String inActivity) throws CompaniesIsEmptyException {
        if (!isEmpty()) {
            Stream<Company> selection;
            selection = (companies.stream()).filter(company -> (company.getActivity()).equalsIgnoreCase(inActivity));
            return selection;
        } else {
            throw new CompaniesIsEmptyException();
        }
    }

    public Stream<Company> filterByDateOfFoundation(String fromDate, String toDate)
            throws CompaniesIsEmptyException {
        if (!isEmpty()) {
            Stream<Company> selection;
            selection = (companies.stream())
                    .filter(company -> CompanyUtil.isDateOfFoundationInInterval(company, fromDate, toDate));
            return selection;
        } else {
            throw new CompaniesIsEmptyException();
        }
    }

    public Stream<Company> filterByCountOfEmployees(int fromCount, int toCount)
            throws CompaniesIsEmptyException {
        if (!isEmpty()) {
            Stream<Company> selection;
            selection = (companies.stream())
                    .filter(company -> CompanyUtil.isCountOfEmployeesInInterval(company, fromCount, toCount));
            return selection;
        } else {
            throw new CompaniesIsEmptyException();
        }
    }

    public String[] getColumnsNames() throws CompaniesIsEmptyException {
        if (!isEmpty()) {
            return Company.getColumnsNames();
        } else {
            throw new CompaniesIsEmptyException();
        }
    }

    public void setColumnsNames(String[] columnsNames) throws CompaniesIsEmptyException {
        if (!isEmpty()) {
            Company.setColumnsNames(columnsNames);
        } else {
            throw new CompaniesIsEmptyException();
        }
    }

    public Iterator<Company> getIterator () {
        return companies.iterator();
    }

    public Stream<Company> getStream() {
        return companies.stream();
    }

    public boolean isEmpty() {
        return companies.isEmpty();
    }

    public FormatXML toXML() throws IncorrectFormatException {
        FormatXML xml = new FormatXML();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<companies>\n");
        for (Company item : companies) {
            xml.append(CompanyUtil.arrayElementToXML(item));
        }
        xml.append("</companies>");
        return xml;
    }

    public FormatXML toXML(Stream<Company> filter) throws IncorrectFormatException {
        FormatXML xml = new FormatXML();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<companies>\n");
        filter.forEach(company -> {
            try {
                xml.append(CompanyUtil.arrayElementToXML(company));
            } catch (IncorrectFormatException e) {
                e.printStackTrace();
            }
        });
        xml.append("</companies>");
        return xml;
    }

    public FormatJSON toJSON() throws IncorrectFormatException {
        FormatJSON json = new FormatJSON();
        Iterator<Company> iterator = companies.iterator();
        Company tmp;
        json.append("{\n\t\"companies\": [");
        while (iterator.hasNext()) {
            tmp = iterator.next();
            if (iterator.hasNext()) {
                json.append(CompanyUtil.arrayElementToJSON(tmp, false));
            } else {
                json.append(CompanyUtil.arrayElementToJSON(tmp, true));
            }
        }
        json.append("\t]\n}");
        return json;
    }

    public FormatJSON toJSON(Stream<Company> filter) throws IncorrectFormatException {
        FormatJSON json = new FormatJSON();
        Iterator<Company> iterator = filter.iterator();
        Company tmp;
        json.append("{\n\t\"companies\": [");
        while (iterator.hasNext()) {
            tmp = iterator.next();
            if (iterator.hasNext()) {
                json.append(CompanyUtil.arrayElementToJSON(tmp, false));
            } else {
                json.append(CompanyUtil.arrayElementToJSON(tmp, true));
            }
        }
        json.append("\n\t]\n}");
        return json;
    }
}
