package by.sem3.lab6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

class Companies {
    private List<Company> companies;

    public Companies(List<String[]> list) throws CompaniesIsEmptyException {
        this.companies = new ArrayList<>();
        Iterator<String[]> iter = list.iterator();
        iter.next();
        while (iter.hasNext()) {
            companies.add(new Company(iter.next()));
        }
        setColumnsNames(list.get(0));
    }

    public void print() throws CompaniesIsEmptyException {
        if (!isEmpty()) {
            printColumnsNames();
            companies.forEach(Company::print);
        } else {
            throw new CompaniesIsEmptyException();
        }
    }

    public void printColumnsNames() throws CompaniesIsEmptyException {
        System.out.format("%9s%16s%19s%19s%28s%23s%12s%12s%20s%20s%15s%20s", getColumnsNames());
        System.out.println();
    }

    public Company findByShortTitle() throws CompaniesIsEmptyException {
        if (!isEmpty()) {
            Scanner scan = new Scanner(System.in);
            String title;
            System.out.print("Enter short title: ");
            title = scan.nextLine();
            for (Company iter : companies) {
                if (title.equalsIgnoreCase(iter.getShortTitle())) {
                    return iter;
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
            selection = (companies.stream()).filter(company -> company.isDateOfFoundationInInterval(fromDate, toDate));
            return selection;
        } else {
            throw new CompaniesIsEmptyException();
        }
    }

    public Stream<Company> filterByCountOfEmployees(int fromCount, int toCount)
            throws CompaniesIsEmptyException {
        if (!isEmpty()) {
            Stream<Company> selection;
            selection = (companies.stream()).filter(company -> company.isCountOfEmployeesInInterval(fromCount, toCount));
            return selection;
        } else {
            throw new CompaniesIsEmptyException();
        }
    }

    public String[] getColumnsNames() throws CompaniesIsEmptyException {
        if (!isEmpty()) {
            return companies.get(0).getColumnsNames();
        } else {
            throw new CompaniesIsEmptyException();
        }
    }

    public void setColumnsNames(String[] columnsNames) throws CompaniesIsEmptyException {
        if (!isEmpty()) {
            companies.get(0).setColumnsNames(columnsNames);
        } else {
            throw new CompaniesIsEmptyException();
        }
    }

    public boolean isEmpty() {
        return companies.isEmpty();
    }

    public FormatXML toXML() throws IncorrectFormatException {
        FormatXML xml = new FormatXML();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<companies>\n");
        for (Company item : companies) {
            xml.append(item.toXML(true));
        }
        xml.append("</companies>");
        return xml;
    }

    public FormatXML toXML(Stream<Company> filter) throws IncorrectFormatException {
        FormatXML xml = new FormatXML();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<companies>\n");
        filter.forEach(company -> {
            try {
                xml.append(company.toXML(true));
            } catch (IncorrectFormatException e) {
                e.printStackTrace();
            }
        });
        xml.append("</companies>");
        return xml;
    }

    public FormatJSON toJSON() throws IncorrectFormatException {
        FormatJSON json = new FormatJSON();
        Iterator<Company> iter = companies.iterator();
        Company tmp;
        json.append("{\n\t\"companies\": [");
        while (iter.hasNext()) {
            tmp = iter.next();
            if (iter.hasNext()) {
                json.append(tmp.toJSON(true, false));
            } else {
                json.append(tmp.toJSON(true, true));
            }
        }
        json.append("\t]\n}");
        return json;
    }

    public FormatJSON toJSON(Stream<Company> filter) throws IncorrectFormatException {
        FormatJSON json = new FormatJSON();
        Iterator<Company> iter = filter.iterator();
        Company tmp;
        json.append("{\n\t\"companies\": [");
        while (iter.hasNext()) {
            tmp = iter.next();
            if (iter.hasNext()) {
                json.append(tmp.toJSON(true, false));
            } else {
                json.append(tmp.toJSON(true, true));
            }
        }
        json.append("\n\t]\n}");
        return json;
    }
}
